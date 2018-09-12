package tv.jiaying.actv.entity.slave;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tv.jiaying.actv.entity.slave.Colum;

public interface ColumRepository extends JpaRepository<Colum, Long> {

    Colum findFirstById(long Id);

    Colum findFirstByName(String name);

    Boolean existsByIsRootTrue();

    //Boolean existsByRootIsTrue();

    Colum findFirstByIsRootIsTrue();

    Page<Colum> findAllByTypeAndNameSpellContainingOrTypeAndActorsSpellContainingOrTypeAndDirectorSpellContaining(Colum.Type type1, String titleSpell, Colum.Type type2, String actorsSpell, Colum.Type type3, String directorSpell, Pageable pageable);

    Page<Colum> findAllByTypeAndNameSpellContaining(Colum.Type type1, String titleSpell, Pageable pageable);

    Page<Colum> findAllByTypeAndActorsSpellContaining(Colum.Type type2, String actorsSpell, Pageable pageable);

    Page<Colum> findAllByTypeAndDirectorSpellContaining(Colum.Type type3, String directorSpell, Pageable pageable);

    Page<Colum> findAllByTypeAndNameSpellContainingOrTypeAndActorsSpellContaining(Colum.Type type1, String titleSpell, Colum.Type type2, String actorsSpell, Pageable pageable);

    Page<Colum> findAllByTypeAndActorsSpellContainingOrTypeAndDirectorSpellContaining( Colum.Type type2, String actorsSpell, Colum.Type type3, String directorSpell, Pageable pageable);

    Page<Colum> findAllByTypeAndNameSpellContainingOrTypeAndDirectorSpellContaining(Colum.Type type1, String titleSpell, Colum.Type type3, String directorSpell, Pageable pageable);

}
