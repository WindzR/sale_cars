package ru.bodin.store;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.bodin.entity.Announcement;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.function.Function;

public class AdRepository implements AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final AdRepository INST = new AdRepository();
    }

    public static AdRepository instOf() {
        return AdRepository.Lazy.INST;
    }

    public List<Announcement> findAnnouncementsByLastDay() {
        return this.tx(
                session -> {
                    List<Announcement> announcements = null;
                    announcements = session.createQuery(
                                    "select distinct ann from Announcement ann"
                                            + " join fetch ann.user "
                                            + "where ann.created BETWEEN :nowDate AND :cntdownDate"
                                            + " order by ann.created")
                            .setParameter("nowDate", LocalDate.now(ZoneId.of("Europe/Moscow")))
                            .setParameter(
                                    "cntdownDate",
                                    LocalDate.now(ZoneId.of("Europe/Moscow")).minusDays(1)
                            )
                            .getResultList();
                    return announcements;
                }
        );
    }

    public List<Announcement> findAnnouncementsWithPhoto() {
        return this.tx(
                session -> {
                    List<Announcement> announcements = null;
                    announcements = session.createQuery(
                                    "select distinct ann from Announcement ann"
                                            + " join fetch ann.image img "
                                            + "where img.link IS NOT NULL"
                                            + " order by ann.created")
                            .getResultList();
                    return announcements;
                }
        );
    }

    public List<Announcement> findAnnouncementsByModel(String model) {
        return this.tx(
                session -> {
                    List<Announcement> announcements = null;
                    announcements = session.createQuery(
                                    "select distinct ann from Announcement ann"
                                            + " join fetch ann.car car "
                                            + "where car.model = :carModel"
                                            + " order by ann.created")
                            .setParameter("carModel", model)
                            .getResultList();
                    return announcements;
                }
        );
    }

    private <T> T tx(final Function<Session, T> command) {
        Session session = sf.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception ex) {
            session.getTransaction().rollback();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() throws Exception {
        try {
            if (sf.isOpen()) {
                sf.close();
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }
}
