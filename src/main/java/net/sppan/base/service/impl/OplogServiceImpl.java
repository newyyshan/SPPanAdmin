package net.sppan.base.service.impl;

import net.sppan.base.common.utils.DateUtils;
import net.sppan.base.dao.IOplogDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Oplog;
import net.sppan.base.service.IOplogService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import net.sppan.base.vo.OpLogVO;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

	@Override
	public IBaseDao<Oplog, Integer> getBaseDao() {
		return this.oplogDao;
	}

	@Override
	public void saveOrUpdate(Oplog oplog) {
		if (oplog.getId() != null) {
			Oplog dbOplog = find(oplog.getId());
			if (oplog.getState() != null) {
				dbOplog.setState(oplog.getState());
			} else {
				dbOplog.setState(1);
			}
			dbOplog.setRegion(oplog.getRegion());
			dbOplog.setSquare(oplog.getSquare());
			dbOplog.setUpdateTime(new Date());
			dbOplog.setUpdateUser(oplog.getCreateUser());
			dbOplog.setRemark(oplog.getRemark());
			dbOplog.setEventname(oplog.getEventname());
			update(dbOplog);
		} else {
			if (oplog.getState() != null) {
				oplog.setState(oplog.getState());
			} else {
				oplog.setState(0);
			}
			oplog.setCreateTime(new Date());
			save(oplog);
		}
	}

	@Override
	public void delete(Integer id) {
		super.delete(id);
	}

	@Override
	public Page<Oplog> findAllByLike(String searchText, PageRequest pageRequest) {
		if (StringUtils.isBlank(searchText)) {
			searchText = "";
		}
		return oplogDao.findAllByEventnameContaining(searchText, pageRequest);
	}

	@Override
	public Page<Oplog> findByConditions(final OpLogVO opLogVO, PageRequest pageRequest) {
		Specification<Oplog> specification = new Specification<Oplog>() {
			@Override
			public Predicate toPredicate(Root<Oplog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicatesList = new ArrayList<>();

				if (opLogVO.getState() != null) {
					Predicate statePredicate = cb.equal(root.get("state"), opLogVO.getState());
					predicatesList.add(statePredicate);
				}

				if (StringUtils.isNotBlank(opLogVO.getRegion())) {
					Predicate regionPredicate = cb.equal(root.get("region"), opLogVO.getRegion());
					predicatesList.add(regionPredicate);
				}

				if (StringUtils.isNotBlank(opLogVO.getSquare())) {
					Predicate squarePredicate = cb.equal(root.get("square"), opLogVO.getSquare());
					predicatesList.add(squarePredicate);
				}

				if (StringUtils.isNotBlank(opLogVO.getCreateUser())) {
					Predicate createUserPredicate = cb.equal(root.get("createUser"), opLogVO.getCreateUser());
					predicatesList.add(createUserPredicate);
				}

				if (StringUtils.isNotBlank(opLogVO.getUpdateUser())) {
					Predicate updateUserPredicate = cb.equal(root.get("updateUser"), opLogVO.getUpdateUser());
					predicatesList.add(updateUserPredicate);
				}

				if (StringUtils.isNotBlank(opLogVO.getEventname())) {
					Predicate eventNamePredicate = cb.like(root.get("eventname").as(String.class), "%" + opLogVO.getEventname() + "%");
					predicatesList.add(eventNamePredicate);
				}

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date earliestTime = new Date(0);
				if (ArrayUtils.isNotEmpty(opLogVO.getCreateTime())) {
					try {
						Date createTimeStart = StringUtils.isNotBlank(opLogVO.getCreateTime()[0])
							? dateFormat.parse(opLogVO.getCreateTime()[0])
							: earliestTime;
						Date createTimeEnd = StringUtils.isNotBlank(opLogVO.getCreateTime()[1])
							? dateFormat.parse(opLogVO.getCreateTime()[1])
							: new Date();
						Predicate createTimePredicate = cb.between(
							root.get("createTime").as(Date.class),
							DateUtils.atStartOfDay(createTimeStart),
							DateUtils.atEndOfDay(createTimeEnd)
						);
						predicatesList.add(createTimePredicate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}

				if (ArrayUtils.isNotEmpty(opLogVO.getUpdateTime())) {
					try {
						Date updateTimeStart = StringUtils.isNotBlank(opLogVO.getUpdateTime()[0])
							? dateFormat.parse(opLogVO.getUpdateTime()[0])
							: earliestTime;
						Date updateTimeEnd = StringUtils.isNotBlank(opLogVO.getUpdateTime()[1])
							? dateFormat.parse(opLogVO.getUpdateTime()[1])
							: new Date();
						Predicate updateTimePredicate = cb.between(
							root.get("updateTime").as(Date.class),
							DateUtils.atStartOfDay(updateTimeStart),
							DateUtils.atEndOfDay(updateTimeEnd)
						);
						predicatesList.add(updateTimePredicate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}

				Predicate[] predicates = new Predicate[predicatesList.size()];
				return cb.and(predicatesList.toArray(predicates));
			}
		};
		return oplogDao.findAll(specification, pageRequest);
	}
}
