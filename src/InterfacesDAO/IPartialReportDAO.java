package InterfacesDAO;

import Domain.PartialReport;
import java.sql.SQLException;

public interface IPartialReportDAO {
    public void savePartialReport(int id_practicing) throws SQLException, ClassNotFoundException;
    public PartialReport readPartialReportByIDPracticing(int id_practicing) throws SQLException, ClassNotFoundException;
    public PartialReport readPartialReportByIDPartial (int id_partial) throws SQLException, ClassNotFoundException;
    public void deletePartialReportByIDPartial (int id_partial) throws SQLException, ClassNotFoundException;
}
