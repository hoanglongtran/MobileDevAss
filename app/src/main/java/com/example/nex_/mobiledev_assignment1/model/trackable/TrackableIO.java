package com.example.nex_.mobiledev_assignment1.model.trackable;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.DatabaseHelper;
import com.example.nex_.mobiledev_assignment1.model.TrackingService;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Scanner;

public class TrackableIO {
    private static final String TAG = "TrackableIO";
    private int trackableId;
    private String trackableName;
    private String trackableDes;
    private String trackableURL;
    private String trackableCategory;


    public void parseFile(Context context)
    {
        // resource reference to tracking_data.txt in res/raw/ folder of your project
        // supports trailing comments with //
        try (Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.food_truck_data)))
        {
            // match comma and 0 or more whitespace OR trailing space and newline
            scanner.useDelimiter(",\"\\s*|\",\"|\"\\s*\\n+");
            while (scanner.hasNext())
            {


                trackableId = Integer.parseInt(scanner.next());
                trackableName = scanner.next();
                trackableDes = scanner.next();
                trackableURL = scanner.next();
                trackableCategory = scanner.next();
                TrackableList.getInstance().addTrackables(trackableId, trackableName,trackableDes,trackableURL,trackableCategory);
                if (DatabaseHelper.getInstance(context).insertTrackaleData(trackableId, trackableName,trackableDes,trackableURL,trackableCategory)){
                    System.out.println("Succeed");

                }else{
                    System.out.println("Failed");
                }
            }
        }
        catch (Resources.NotFoundException e)
        {
            Log.i(TAG, "File Not Found Exception Caught");
        }
    }

}
