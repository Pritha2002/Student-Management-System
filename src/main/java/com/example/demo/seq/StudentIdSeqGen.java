package com.example.demo.seq;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class StudentIdSeqGen implements IdentifierGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prefix = "STUDENT_REG";

	private void init(SharedSessionContractImplementor session) {

		String tableExist = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name='STUDENT_REG_SEQ'";
		String createTable = "CREATE TABLE STUDENT_REG_SEQ(STUDENT_ID INT)";
		String initialValues = "INSERT INTO STUDENT_REG_SEQ(STUDENT_ID) VALUES(1000)";

		try (Connection con = session.getJdbcConnectionAccess().obtainConnection();
				Statement st = con.createStatement()) {

			ResultSet rs = st.executeQuery(tableExist);
			if (rs.next() && rs.getInt(1) == 0) {
				st.executeUpdate(createTable);
				st.executeUpdate(initialValues);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		String suffix = null;

		try (Connection con = session.getJdbcConnectionAccess().obtainConnection();
				Statement st = con.createStatement()) {

			init(session);

			String selectQuery = "SELECT STUDENT_ID FROM STUDENT_REG_SEQ";
			ResultSet rs = st.executeQuery(selectQuery);

			if (rs.next()) {
				suffix = rs.getString(1);
				String updateValue = "UPDATE STUDENT_REG_SEQ SET STUDENT_ID = STUDENT_ID +1";
				st.executeUpdate(updateValue);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return prefix + suffix;
	}

}
