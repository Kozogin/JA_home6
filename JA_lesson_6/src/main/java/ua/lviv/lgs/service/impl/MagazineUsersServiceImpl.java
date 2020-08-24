package ua.lviv.lgs.service.impl;

import java.util.List;

import ua.lviv.lgs.dao.MagazineUsersDao;
import ua.lviv.lgs.dao.impl.MagazineUsersDaoImpl;
import ua.lviv.lgs.domain.MagazineUsers;
import ua.lviv.lgs.service.MagazineUsersService;

public class MagazineUsersServiceImpl implements MagazineUsersService{
	
	public static MagazineUsersService magazineUsersServiceImpl;
	private MagazineUsersDao magazineUsersDao;
	
	private MagazineUsersServiceImpl(){		
			magazineUsersDao = new MagazineUsersDaoImpl();		
	}
	
	public static MagazineUsersService getMagazineUsersService() {
		if(magazineUsersServiceImpl == null) {
			magazineUsersServiceImpl = new MagazineUsersServiceImpl();
		}
		return magazineUsersServiceImpl;		
	}

	@Override
	public MagazineUsers create(MagazineUsers t) {
		return magazineUsersDao.create(t);
	}

	@Override
	public MagazineUsers read(Integer id) {
		return magazineUsersDao.read(id);
	}

	@Override
	public MagazineUsers update(MagazineUsers t) {
		return magazineUsersDao.update(t);
	}

	@Override
	public void delete(Integer id) {
		magazineUsersDao.delete(id);
	}

	@Override
	public List<MagazineUsers> readAll() {
		return magazineUsersDao.readAll();
	}

}
