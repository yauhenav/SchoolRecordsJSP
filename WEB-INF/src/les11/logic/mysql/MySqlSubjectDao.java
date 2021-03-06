package les11.logic.mysql;

import java.sql.*;
import java.util.*;

import les11.logic.dao.*;
import les11.logic.dto.*;
import les11.logic.exception.*;

public class MySqlSubjectDao implements SubjectDao {

    private final static String SQL_CREATE = "INSERT INTO daotrain.SUBJECT (ID, DESCRIPTION) VALUES (?, ?)";
    private final static String SQL_READ = "SELECT ID, DESCRIPTION FROM daotrain.SUBJECT WHERE ID = ?";
    private final static String SQL_UPDATE = "UPDATE daotrain.SUBJECT SET DESCRIPTION = ? WHERE ID = ?";
    private final static String SQL_DELETE = "DELETE FROM daotrain.SUBJECT WHERE ID = ?";
    private final static String SQL_GETALL = "SELECT ID, DESCRIPTION FROM daotrain.SUBJECT";

    private PreparedStatement psCreateSubj = null;
    private PreparedStatement psReadSubj = null;
    private PreparedStatement psUpdSubj = null;
    private PreparedStatement psDelSubj = null;
    private PreparedStatement psGetAllSubj = null;

    // Constructor
    public MySqlSubjectDao(Connection connection) throws DaoException {
        try {
            psCreateSubj = connection.prepareStatement(SQL_CREATE);
            psReadSubj = connection.prepareStatement(SQL_READ);
            psUpdSubj = connection.prepareStatement(SQL_UPDATE);
            psDelSubj = connection.prepareStatement(SQL_DELETE);
            psGetAllSubj = connection.prepareStatement (SQL_GETALL);
        } catch (SQLException exc) {
            throw new DaoException ("Exception for DAO", exc);
        }
    }

    // Create a new DB entry as per corresponding received object
    @Override
    public void create(Subject subject) throws DaoException {
        try {
            psCreateSubj.setInt(1, subject.getId());
            psCreateSubj.setString(2, subject.getDescription());
            psCreateSubj.execute();
        } catch (SQLException exc) {
            throw new DaoException ("Exception for DAO", exc);
        }
    }

    // Return the object corresponding to the DB entry with received primary 'key'
    @Override
    public Subject read(Subject subject) throws DaoException {
        ResultSet rsReadSubj = null;
        try {
            psReadSubj.setInt(1, subject.getId());
            rsReadSubj = psReadSubj.executeQuery();
            rsReadSubj.next();
            subject.setId(rsReadSubj.getInt("ID"));
            subject.setDescription(rsReadSubj.getString("DESCRIPTION"));
            return subject;
        } catch (SQLException exc) {
            throw new DaoException ("Exception for Dao", exc);
        }
        finally {
            try {
                if (rsReadSubj != null) {
                    rsReadSubj.close();
                } else {
                    System.err.println ("RS set of table results was not created");
                }
            } catch (SQLException exc) {
                throw new DaoException ("Exception for DAO", exc);
            }
        }
    }

    // Modify the DB entry as per corresponding received object
    @Override
    public void update(Subject subject) throws DaoException {
        try {
            psUpdSubj.setString(1, subject.getDescription());
            psUpdSubj.setInt(2, subject.getId());
            psUpdSubj.execute();
        } catch (SQLException exc) {
            throw new DaoException ("Exception for DAO", exc);
        }
    }

    // Remove the DB entry as per corresponding received object
    @Override
    public void delete(Subject subject) throws DaoException {
        try {
            psDelSubj.setInt(1, subject.getId());
            psDelSubj.execute();
        } catch (SQLException exc) {
            throw new DaoException ("Exception for DAO", exc);
        }
    }

    // Return a list of objects corresponding to all DB entries
    @Override
    public List<Subject> getAll() throws DaoException {
        ResultSet rsGetAllSubj = null;
        try {
            rsGetAllSubj = psGetAllSubj.executeQuery();
            List<Subject> list = new ArrayList<Subject>();
            while (rsGetAllSubj.next()) {
                Subject tempSubj1 = new Subject(0, null);
                tempSubj1.setId(rsGetAllSubj.getInt("ID"));
                tempSubj1.setDescription(rsGetAllSubj.getString("DESCRIPTION"));
                list.add(tempSubj1);
            }
            return list;
        } catch (SQLException exc) {
            throw new DaoException ("Exception for DAO", exc);
        }
        finally {
            try {
                if (rsGetAllSubj != null) {
                    rsGetAllSubj.close();
                } else {
                    System.err.println ("RS set of table results was not created");
                }
            } catch (SQLException exc) {
                throw new DaoException ("Exception for DAO", exc);
            }
        }
    }

    // Terminate 'PreparedStatement' object received as an argument
    private void closePs(PreparedStatement dummyPs) throws DaoException {
        if (dummyPs != null) {
            try {
                dummyPs.close();
                //throw new SQLException(); // Uncomment this line to test exception handling
            } catch (SQLException exc) {
                throw new DaoException("Exception for Dao", exc);
            }
        } else {
            System.err.println ("PS statement was not created");
        }
    }

    // Terminate all 'PreparedStatement' objects
    public void close() throws DaoException {
        DaoException exc = null;
        try {
            try {
                closePs(psCreateSubj);
            } catch (DaoException e) {
                exc = e;
            }
            try {
                closePs(psReadSubj);
            } catch (DaoException e) {
                exc = e;
            }
            try {
                closePs(psUpdSubj);
            } catch (DaoException e) {
                exc = e;
            }
            try {
                closePs(psDelSubj);
            } catch (DaoException e) {
                exc = e;
            }
        } finally {
            if (exc != null) {
                throw exc;
            }
        }
    }
}
