package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Oplog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOplogDao extends IBaseDao<Oplog, Integer> {

//	@Modifying
//	@Query(nativeQuery = true,value = "DELETE FROM tb_role_resource WHERE resource_id = :id")
//	void deleteGrant(@Param("id") Integer id);

	Page<Oplog> findAllByEventnameContaining(String searchText, Pageable pageable);

}
