package com.example.nex_.mobiledev_assignment1.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;

// simple Android JSON example by Caspar
public class JSONTester
{
   private static final String LOG_TAG = JSONTester.class.getName();

   private static final String jsonString =
           "{" +
                   "\"firstName\": \"John\", " +
                   "\"lastName\" : \"Smith\", " +
                   "\"age\"      : 25," +
                   "\"address\"  : " +
                   "{" +
                   "\"streetAddress\": \"21 2nd Street\"," +
                   "\"city\"         : \"New York\"," +
                   "\"state\"        : \"NY\"," +
                   "\"postalCode\"   : \"10021\"" +
                   "}," +
                   "\"phoneNumbers\":" +
                   "[" +
                   "{" +
                   "\"type\"  : \"home\"," +
                   "\"number\": \"212 555-1234\"" +
                   "}," +
                   "{" +
                   "\"type\"  : \"fax\"," +
                   "\"number\": \"646 555-4567\"" +
                   "}" +
                   "]" +
                   "}";

   /*
{
     "firstName": "John",
     "lastName" : "Smith",
     "age"      : 25,
     "address"  :
     {
         "streetAddress": "21 2nd Street",
         "city"         : "New York",
         "state"        : "NY",
         "postalCode"   : "10021"
     },
     "phoneNumbers":
     [
         {
           "type"  : "home",
           "number": "212 555-1234"
         },
         {
           "type"  : "fax",
           "number": "646 555-4567"
         }
     ]
 }
*/
   public static void testJSON() throws Exception
   {
      JSONObject person = new JSONObject(jsonString);
      JSONObject address = person.getJSONObject("address");
      JSONArray phoneNums = person.getJSONArray("phoneNumbers");

      String firstName = person.getString("firstName");
      String city = address.getString("city");

      Log.i(LOG_TAG, "firstName:" + firstName);
      Log.i(LOG_TAG, "city:" + city);

      for (int i = 0; i < phoneNums.length(); i++)
         Log.i(LOG_TAG,
                 String.format(Locale.getDefault(), "phone[%d]: %s", i, phoneNums.getJSONObject(i)
                         .getString("number")));
   }
}
