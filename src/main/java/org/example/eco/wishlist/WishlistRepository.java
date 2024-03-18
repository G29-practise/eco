package org.example.eco.wishlist;
import org.example.eco.common.repository.GenericRepository;
import org.example.eco.wishlist.entity.Wishlist;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface WishlistRepository extends GenericRepository<Wishlist, UUID> {

}
