package com.example.restservicefull.repositories;

import com.example.restservicefull.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
