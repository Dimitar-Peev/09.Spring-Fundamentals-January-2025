package com.paintingscollectors.repository;

import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, String> {

    List<Painting> findAllByOwner(User owner);

    List<Painting> findAllByOwnerNot(User user);

    @Query(nativeQuery = true, value = "SELECT p.* FROM paintings p " +
            "JOIN users_favorite_paintings f ON p.id = f.favorite_paintings_id WHERE f.user_id = :userId;")
    List<Painting> findFavoritePaintings(String userId);

    List<Painting> findTop2ByOrderByVotesDescNameAsc();

    Painting findPaintingById(String paintingId);

}