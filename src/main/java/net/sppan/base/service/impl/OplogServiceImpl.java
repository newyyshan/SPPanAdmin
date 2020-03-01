package net.sppan.base.service.impl;

import net.sppan.base.common.Constats;
import net.sppan.base.dao.IOplogDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Oplog;
import net.sppan.base.entity.Oplog;
import net.sppan.base.entity.Role;
import net.sppan.base.service.IOplogService;
import net.sppan.base.service.IRoleService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import net.sppan.base.vo.ZtreeView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 日志表 运维日志实现类
 * </p>
 *
 * @author SPPan
 * @since 2020-02-29
 */
@Service
public class OplogServiceImpl extends BaseServiceImpl<Oplog, Integer>
		implements IOplogService {

	@Autowired
	private IOplogDao oplogDao;

//	@Autowired
//	private IRoleService roleService;

	@Override
	public IBaseDao<Oplog, Integer> getBaseDao() {
		return this.oplogDao;
	}

//	@Override
//	@Cacheable(value=Constats.RESOURCECACHENAME,key="'tree_' + #roleId")
//	public List<ZtreeView> tree(int roleId) {
//		List<ZtreeView> resulTreeNodes = new ArrayList<ZtreeView>();
//		Role role = roleService.find(roleId);
//		Set<Oplog> roleOplogs = role.getOplogs();
//		resulTreeNodes.add(new ZtreeView(0L, null, "系统菜单", true));
//		ZtreeView node;
//		List<Oplog> all = oplogDao.findAllByOrderByParentAscIdAscSortAsc();
//		for (Oplog oplog : all) {
//			node = new ZtreeView();
//			node.setId(Long.valueOf(oplog.getId()));
//			if (oplog.getParent() == null) {
//				node.setpId(0L);admin
//			} else {
//				node.setpId(Long.valueOf(oplog.getParent().getId()));
//			}
//			node.setName(oplog.getName());
//			if (roleOplogs != null && roleOplogs.contains(oplog)) {
//				node.setChecked(true);
//			}
//			resulTreeNodes.add(node);
//		}
//		return resulTreeNodes;
//	}

	@Override
	public void saveOrUpdate(Oplog oplog) {
		if(oplog.getId() != null){
			Oplog dbOplog = find(oplog.getId());
			if (oplog.getState() !=null){
				dbOplog.setState(oplog.getState());
			}else {
				dbOplog.setState(1);
			}
			dbOplog.setRegion(oplog.getRegion());
			dbOplog.setSquare(oplog.getSquare());
			dbOplog.setUpdateTime(new Date());
			dbOplog.setUpdateUser(oplog.getCreateUser());
			dbOplog.setRemark(oplog.getRemark());
			dbOplog.setEventname(oplog.getEventname());
			update(dbOplog);
		}else{
			oplog.setState(0);
			oplog.setCreateTime(new Date());
			save(oplog);
		}
	}

	@Override
	public void delete(Integer id) {
//		Oplog role = find(id);
		super.delete(id);
	}

	@Override
	public Page<Oplog> findAllByLike(String searchText, PageRequest pageRequest) {
		if(StringUtils.isBlank(searchText)){
			searchText = "";
		}
		return oplogDao.findAllByEventnameContaining(searchText, pageRequest);


	}
	
}
