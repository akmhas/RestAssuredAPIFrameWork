package helpers;

public enum APIResources {

	STUDENT_DETAILS_POST_API("/api/studentsDetails"), 
	STUDENTDETAILSGETAPI("/api/Students"), 
	STUDENTDETAILSDELETEAPI("api/studentsDetails");

	private String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResouce() {
		return resource;
	}
	
	public static String getValueFromEnum(APIResources apiResources){
		for (APIResources api : APIResources.values()) {
			if(api.equals(apiResources)){
				return api.getResouce();
			}			
		}
		throw new IllegalArgumentException("Invalid Enum type passed");
	}

}
