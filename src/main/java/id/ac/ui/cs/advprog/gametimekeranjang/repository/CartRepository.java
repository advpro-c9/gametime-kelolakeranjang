package id.ac.ui.cs.advprog.gametimekeranjang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.gametimekeranjang.model.CartItems;

@Repository
public interface CartRepository extends JpaRepository<CartItems, String> {
   CartItems findByEmail(String email);
}

