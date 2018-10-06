
package com.example.nex_.mobiledev_assignment1.model;

import android.os.AsyncTask;
import android.util.Log;

import mad.topic7.Topic7Activity;

// example of HttpURLConnection by Caspar, updated sem 2, 2018
public abstract class AbstractHttpAsyncTask extends AsyncTask<Void, Integer, Void>
{
   private static final String LOG_TAG = AbstractHttpAsyncTask.class.getName();

   protected StringBuilder htmlStringBuilder = new StringBuilder();

   // this one has a valid CONTENT_LENGTH header
   public static final String TEST_URL = "https://developer.android.com/";

   // this one does not always set the content length so makes progress tracking
   // difficult
   // static final String TEST_URL = "http://www.wikipedia.org/";

   protected Topic7Activity activity = null;
   private int charsRead;

   public AbstractHttpAsyncTask(Topic7Activity activity)
   {
      this.activity = activity;
   }

   @Override
   protected void onProgressUpdate(Integer... progress)
   {
      if (activity == null)
         Log.w(LOG_TAG, "onProgressUpdate() skipped -- no activity");
      else
      {
         // Log.i(LOG_TAG, "Task progress=" + progress[0] + "%");
         activity.updateProgress(progress[0]);
      }
   }

   protected void doProgress(int charsRead, int length)
   {
      this.charsRead += charsRead;
      // delay allows us to see progress on fast network!
      //Thread.sleep(1);
      // convert to percentage for progress update (standard 0..100 range)
      int progress = (int) ((double) this.charsRead / length * 100.0);
      Log.i(LOG_TAG, Integer.toString(progress) + "%");
      publishProgress(progress);
   }

   @Override
   protected void onPostExecute(Void result)
   {
      activity.displayHTML(htmlStringBuilder.toString());
   }
}

