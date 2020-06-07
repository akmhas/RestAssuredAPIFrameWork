package helpers;

public class ResponseBase {
	
	
	public String studentDetailsResponseBase(String id){
		
		
		String stBaseResponse="{\"id\":"+id+",\"first_name\":\"Post Test\",\"middle_name\":\"Post middle\",\"last_name\":\"Post last\",\"date_of_birth\":\"02/7/2020\"}";
		return stBaseResponse;
	}

}
