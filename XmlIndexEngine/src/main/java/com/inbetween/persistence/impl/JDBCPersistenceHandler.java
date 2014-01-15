/**
 * 
 */
package com.inbetween.persistence.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.inbetween.domain.BookValueObject;
import com.inbetween.exception.IndexEngineException;
import com.inbetween.persistence.IndexEngineQueries;
import com.inbetween.persistence.PersistenceHandler;

/**
 * <code>JDBCPersistenceHandler</code> is a implementation of PersistenceHandler, which caters to
 * persisting the data into an RDBMS. This class provides implementation for 
 * simple spring JDBC, more specific handlers for Hibernate, Ibatis etc. can be written. 
 * Date Feb 26, 2013
 * @author Raghvendra Garg
 */
public class JDBCPersistenceHandler implements PersistenceHandler {
	
	private static Log logger = LogFactory.getLog(JDBCPersistenceHandler.class);
	
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	/* (non-Javadoc)
	 * @see com.inbetween.persistence.PersistenceHandler#getAllBooks()
	 */
	public List<BookValueObject> getAllBooks() {
		@SuppressWarnings("unchecked")
		List<BookValueObject> books = jdbcTemplate.query(IndexEngineQueries.getAllBookQuery(), new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				BookValueObject book = new BookValueObject();
				book.setBookIsbnNumber(rs.getString("BOOK_ID"));
				book.setAuthorNames(rs.getString("AUTHOR_NAMES"));
				book.setBookName(rs.getString("BOOK_NAME"));
				return book;
			}
		});
		return books;
	}

	/* (non-Javadoc)
	 * @see com.inbetween.persistence.PersistenceHandler#getBook(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public BookValueObject getBook(String id) {
		List<BookValueObject> books = jdbcTemplate.query(IndexEngineQueries.getBookQuery(), new String[]{id}, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs,  int arg1) throws SQLException {
				BookValueObject book = new BookValueObject();
				book.setBookIsbnNumber(rs.getString("BOOK_ID"));
				book.setAuthorNames(rs.getString("AUTHOR_NAMES").replace("|", ", "));
				book.setBookName(rs.getString("BOOK_NAME"));
				String bookDtls = new String(rs.getBytes("BOOK_DTLS"));
				book.setBookDetails(bookDtls);
				return book;
			}
		});
		if(books != null && !books.isEmpty()){
			return books.get(0);
		}
		logger.info("book object fetched from DB for Id "+ id);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.inbetween.persistence.PersistenceHandler#persistBooks(java.util.List)
	 */
	public void persistBooks(final List<BookValueObject> books)
			throws IndexEngineException {
		if (books != null && !books.isEmpty()) {
			try {
				jdbcTemplate.batchUpdate(IndexEngineQueries.getInsertBook(),
						new BatchPreparedStatementSetter() {
							@Override
							public void setValues(
									PreparedStatement prepStatmnt, int index)
									throws SQLException {
								BookValueObject bookValueObject = books .get(index);
								prepStatmnt.setString(1, bookValueObject.getBookIsbnNumber());
								prepStatmnt.setString(2, bookValueObject.getBookName());
								prepStatmnt.setString(3, bookValueObject.getAuthorNames());
								prepStatmnt.setString(4, bookValueObject.getBookDetails());

							}

							@Override
							public int getBatchSize() {
								return books.size();
							}
						});
			} catch (Exception exception) {
				throw new IndexEngineException(
						"File have duplicate records, please check");
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookValueObject> getBooks(Set<String> ids) {
		String idsStr = getIdsString(ids);
		List<BookValueObject> books = jdbcTemplate.query(IndexEngineQueries.getBooksQuery(), new String[] {idsStr} ,new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs,  int arg1) throws SQLException {
				BookValueObject book = new BookValueObject();
				book.setBookIsbnNumber(rs.getString("BOOK_ID"));
				String bookDtls = new String(rs.getBytes("BOOK_DTLS"));
				book.setBookDetails(bookDtls);
				return book;
			}
		});
		return books;
	}

	private String getIdsString(Set<String> ids) {
		String s = ids.toString();
		s = s.replace("[", "");
		s = s.replace("]", "");
		return s;
	}
	
}
