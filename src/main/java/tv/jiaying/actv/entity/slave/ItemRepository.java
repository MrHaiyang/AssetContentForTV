package tv.jiaying.actv.entity.slave;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tv.jiaying.actv.entity.slave.Item;

public interface ItemRepository extends JpaRepository<Item,Long> {
    Item findFirstById(long id);

    Page<Item> findAllByTitleSpellContainingOrActorsSpellContainingOrDirectorSpellContaining(String titleSpell, String actorsSpell, String directorSpell, Pageable pageable);

    Page<Item> findAllByShowTypeAndTitleSpellContainingOrShowTypeAndActorsSpellContainingOrShowTypeAndDirectorSpellContaining(Item.ShowType showType, String titleSpell,Item.ShowType showType1, String actorsSpell,Item.ShowType showType2, String directorSpell, Pageable pageable);

    Page<Item> findAllByShowTypeAndTitleSpellContaining(Item.ShowType showType, String titleSpell, Pageable pageable);

    Page<Item> findAllByShowTypeAndActorsSpellContaining(Item.ShowType showType1, String actorsSpell, Pageable pageable);

    Page<Item> findAllByShowTypeAndDirectorSpellContaining(Item.ShowType showType2, String directorSpell, Pageable pageable);

    Page<Item> findAllByShowTypeAndTitleSpellContainingOrShowTypeAndActorsSpellContaining(Item.ShowType showType, String titleSpell,Item.ShowType showType1, String actorsSpell, Pageable pageable);

    Page<Item> findAllByShowTypeAndActorsSpellContainingOrShowTypeAndDirectorSpellContaining(Item.ShowType showType1, String actorsSpell,Item.ShowType showType2, String directorSpell, Pageable pageable);

    Page<Item> findAllByShowTypeAndTitleSpellContainingOrShowTypeAndDirectorSpellContaining(Item.ShowType showType, String titleSpell,Item.ShowType showType2, String directorSpell, Pageable pageable);



}
