package helpers;

import domain.studentDetailsPost.StudentDetailsPostRequest;

public class LoadData {
	
	
	public String id=null;
	
	
	public StudentDetailsPostRequest getstudentDetailsPostData(String firstName,String lastName,String middleName,String dateofBirth){
		
		StudentDetailsPostRequest stDetailsrequest= new StudentDetailsPostRequest();
		stDetailsrequest.setFirstName(firstName);
		stDetailsrequest.setLastName(lastName);
		stDetailsrequest.setMiddleName(middleName);
		stDetailsrequest.setDateOfBirth(dateofBirth);
        return stDetailsrequest;
	}
	
	
	

	
	
	/*public StudentTechnicalSkillRequest getStudentTechnicalPostData(List<String> language,String yearexp,String lastused,String stId){
		
		StudentTechnicalSkillRequest technicalSkill=new StudentTechnicalSkillRequest();
		//technicalSkill.setId(id);
		technicalSkill.setLanguage(language);
		technicalSkill.setYearexp(yearexp);
		technicalSkill.setLastused(lastused);
		technicalSkill.setStId(stId);
		return technicalSkill;
			
	}*/

}
