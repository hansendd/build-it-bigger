package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest  {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRuleMainActivity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testJokeRetrieval() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(activityTestRuleMainActivity.getActivity()) {
            @Override
            protected void onPostExecute(String joke) {
                assertNotNull(joke);
                assertTrue("A blank joke was returned", joke.length() > 0);
                countDownLatch.countDown();
            }
        };
        endpointsAsyncTask.execute();
        countDownLatch.await();
    }
}
