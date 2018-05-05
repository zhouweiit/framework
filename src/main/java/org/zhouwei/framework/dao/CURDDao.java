
package org.zhouwei.framework.dao;

import java.io.Serializable;

/**
 * 抽象的统一常用的Dao操作方法的接口
 *
 * @author zhouwei
 */
public interface CURDDao<T, ID extends Serializable> {

	/**
	 * 软删除，is_delete 设置为 1
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	int deleteSoftByPrimaryKey(ID id) throws DaoException;

	/**
	 * 物理删除
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	int deleteByPrimaryKey(ID id) throws DaoException;

	int insert(T record) throws DaoException;


	T selectByPrimaryKey(ID id) throws DaoException;


	int updateByPrimaryKey(T record) throws DaoException;

}
