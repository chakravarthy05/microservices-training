package com.slokam.rec.repo;
				import org.springframework.data.jpa.repository.JpaRepository;
				import org.springframework.stereotype.Repository;
				import com.slokam.rec.entity.Offer;
				@Repository
				public interface OfferRepo extends JpaRepository<Offer, Integer> {

				}
