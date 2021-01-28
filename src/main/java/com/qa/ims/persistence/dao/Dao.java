package com.qa.ims.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

	List<T> readAll();

	T create(T t);

	T update(T t);

	void delete(long id);

	T domainFromResultSet(ResultSet resultSet) throws SQLException;
}
