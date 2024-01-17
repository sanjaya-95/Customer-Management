package com.example.shoppingnet.repo;

import com.example.shoppingnet.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item, Integer> {
    List<Item> findBySupplierPrice(Double price);

    @Query( value = "select * from item where item_name = ?1", nativeQuery = true)
    List<Item> searchByxyz(String name);

    @Query( value = "select i.item_id, c.customer_address from item i JOIN customer c ON i.item_id = c.customer_id where item_name = ?1", nativeQuery = true)
    List<Tuple> searchByxyz2(String name);


    List<Item> findAllByActiveStatusEquals(boolean activeStatus);

    List<Item> findAllByItemNameEqualsAndActiveStatusEquals(String itemName, boolean b);

//    Page<Item> findAllByPages(Pageable pageable);
}
