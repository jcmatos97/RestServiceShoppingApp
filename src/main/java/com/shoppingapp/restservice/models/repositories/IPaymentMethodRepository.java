package com.shoppingapp.restservice.models.repositories;

        import com.shoppingapp.restservice.models.PaymentMethod;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;

public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
        @Query(value = "SELECT u.id, u.name, u.lastname, u.username, u.email, u.password, u.status " +
                "FROM user u JOIN payment_method pm ON u.id = pm.id_user " +
                "WHERE t.id = :idPARAM", nativeQuery = true)
        Object getUser(@Param("idPARAM") int id);
}
