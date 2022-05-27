package com.pucese.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pucese.ecommerce.model.User;
import com.pucese.ecommerce.model.Wishlist;
import java.util.List;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {
	List<Wishlist> findAllByUserOrderByCreatedDateDesc(User user);
}
