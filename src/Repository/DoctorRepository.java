package Repository;
import DataAccess.DoctorDao;

public class DoctorRepository implements IDoctorRepository {
    @Override
    public void addupdateDoctor() {
        DoctorDao.instance().addupdateDoctor();
    }

    @Override
    public void deleteDoctor() {
        DoctorDao.instance().deleteDoctor();
    }

    @Override
    public void searchDoctor() {
        DoctorDao.instance().searchDoctor();
    }

}
