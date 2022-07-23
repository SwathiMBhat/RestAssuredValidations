package RCBValidationTestCasesPackage;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class RCBPlayersValidation {

	@Test
	public void validateForeignPlayers() {
		
		baseURI = "http://localhost:3000/";
		int count = 0;
		
		String response = given()
			.get("/player").
		then()
			.statusCode(200)
			.log().all().extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String country = js.get("country").toString();
		System.out.println(country);
		String[] s1=country.split(",");
		for(int i=0;i<s1.length;i++)
		{
			if(s1[i].contains("India")) {
				count = count +0;
			}
			else {
				count = count +1;
			}
		}

		Assert.assertEquals(count, 4);
	}
	
	
	@Test
	public void validateAtleastOneWicketKeeper() {
		
		baseURI = "http://localhost:3000/";
		
		
		String response = given()
			.get("/player").
		then()
			.statusCode(200)
			.log().all().extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String roles = js.get("role").toString();
		System.out.println(roles);
		String[] s1=roles.split(",");
		for(int i=0;i<s1.length;i++)
		{
			if(s1[i].contains("Wicket-keeper")) {
				Assert.assertTrue(true);
			}
		}
	}
	
}
