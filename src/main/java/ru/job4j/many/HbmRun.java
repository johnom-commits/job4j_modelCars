package ru.job4j.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            ModelCar kalina = ModelCar.of("Kalina");
            session.save(kalina);
            ModelCar oka = ModelCar.of("Oka");
            session.save(oka);
            ModelCar priora = ModelCar.of("Priora");
            session.save(priora);
            ModelCar granta = ModelCar.of("Granta");
            session.save(granta);
            ModelCar niva = ModelCar.of("Niva");
            session.save(niva);

            BrandCar brand = BrandCar.of("Lada");
            brand.addModel(session.load(ModelCar.class, 1));
            brand.addModel(session.load(ModelCar.class, 2));
            brand.addModel(session.load(ModelCar.class, 3));
            brand.addModel(session.load(ModelCar.class, 4));
            brand.addModel(session.load(ModelCar.class, 5));

            session.save(brand);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
