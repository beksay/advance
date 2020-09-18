package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.ModuleDao;
import org.infosystema.advance.dao.impl.ModuleDaoImpl;
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.service.ModuleService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(ModuleService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ModuleServiceImpl extends GenericServiceImpl<Module, Integer, ModuleDao> implements ModuleService {

	@Override
	protected ModuleDao getDao() {
		return new ModuleDaoImpl(em, se);
	}

	/*@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void checkForFilled(Project project) {
		System.out.println("project.getTempStatus() = " + project.getTempStatus());
		if(project.getTempStatus() == null) {
			project.setTempStatus(ProjectTempStatus.HAVE_EVER_FILLED);
			System.out.println(project.getTempStatus());
			em.merge(project);
		}
		
		List<FilterExample> list = new ArrayList<>();
		list.add(new FilterExample("index", 14, InequalityConstants.NOT_EQUAL));
		list.add(new FilterExample("project", project, InequalityConstants.EQUAL));
		list.add(new FilterExample("status", Arrays.asList(new ModuleStatus[] {ModuleStatus.DEVELOPMENT, ModuleStatus.NEW, ModuleStatus.QUESTTION}), InequalityConstants.IN));
		
		long count = getDao().countByExample(list);
		
		if(count == 0) {
			project = em.find(Project.class, project.getId());
			project.setStatus(ProjectStatus.FILLED);
			em.merge(project);
		}
		else {
			project = em.find(Project.class, project.getId());
			project.setStatus(ProjectStatus.NEW);
			em.merge(project);
		}
		
	}*/

	/*@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void checkForValidated(Project project) {
		List<FilterExample> list = new ArrayList<>();
		list.add(new FilterExample("index", 14, InequalityConstants.NOT_EQUAL));
		list.add(new FilterExample("project", project, InequalityConstants.EQUAL));
		list.add(new FilterExample("status", ModuleStatus.QUESTTION, InequalityConstants.EQUAL));
		
		long count = getDao().countByExample(list);
		
		if(count > 0) {
			project = em.find(Project.class, project.getId());
			project.setTempStatus(ProjectTempStatus.HAVE_GUESTION);
			em.merge(project);
		}
		else {
			list = new ArrayList<>();
			list.add(new FilterExample("index", 14, InequalityConstants.NOT_EQUAL));
			list.add(new FilterExample("project", project, InequalityConstants.EQUAL));
			list.add(new FilterExample("status", Arrays.asList(new ModuleStatus[] {ModuleStatus.DEVELOPMENT, ModuleStatus.NEW, ModuleStatus.FILLED, ModuleStatus.QUESTTION}), InequalityConstants.IN));
			
			count = getDao().countByExample(list);
			
			if(count == 0) {
				project = em.find(Project.class, project.getId());
				project.setTempStatus(ProjectTempStatus.CHECKED);
				em.merge(project);
			}
			else {
				project.setTempStatus(null);
				em.merge(project);
			}
		}
	}*/

}
