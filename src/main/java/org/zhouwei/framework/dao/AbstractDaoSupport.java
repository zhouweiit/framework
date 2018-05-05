
package org.zhouwei.framework.dao;

import java.io.Serializable;

import org.mybatis.spring.SqlSessionTemplate;

import lombok.Getter;

/**
 * 抽象的统一常用的Dao操作方法
 *
 * @author zhouwei
 */
@Getter
public abstract class AbstractDaoSupport<T, ID extends Serializable> implements CURDDao<T, ID> {

	protected abstract SqlSessionTemplate getSqlSessionTemplate();

	protected abstract String getNamespace();

	public int deleteSoftByPrimaryKey(ID id) throws DaoException{
		try {
			getSqlSessionTemplate().delete(getNamespace() + ".deleteSoftByPrimaryKey", id);
			return 0;
		} catch (Exception e) {
			throw new DaoException(e.getMessage(),e);
		}
	}

	public int deleteByPrimaryKey(ID id) throws DaoException{
		try {
			getSqlSessionTemplate().delete(getNamespace() + ".deleteByPrimaryKey", id);
			return 0;
		} catch (Exception e) {
			throw new DaoException(e.getMessage(),e);
		}
	}

	public int insert(T record) throws DaoException{
		try {
    			return getSqlSessionTemplate().insert(getNamespace() + ".insert", record);
		} catch (Exception e) {
			throw new DaoException(e.getMessage(),e);
		}
	}

	public T selectByPrimaryKey(ID id) throws DaoException{
		try {
			T t = getSqlSessionTemplate().selectOne(getNamespace() + ".selectByPrimaryKey", id);
			return t;
		} catch (Exception e) {
			throw new DaoException(e.getMessage(),e);
		}
	}

	public int updateByPrimaryKey(T record) throws DaoException{
		try {
			getSqlSessionTemplate().update(getNamespace() + ".updateByPrimaryKey", record);
			return 0;
		} catch (Exception e) {
			throw new DaoException(e.getMessage(),e);
		}
	}
}
