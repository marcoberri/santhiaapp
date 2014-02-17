/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */package it.marcoberri.santhiaapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class FragmentLayout extends Activity {

	protected static String TAG = FragmentLayout.class.getName();

	protected boolean isLeftClosed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);

		final Window window = getWindow();
		window.requestFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.fragment_layout);
		window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title_layout);

		final Button refresh = (Button) findViewById(R.id.refreshButton);
		refresh.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				FragmentManager fragmentManager = getFragmentManager();
				

				 
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				

				final FragmentLeft fragmentLeft = (FragmentLeft) fragmentManager
						.findFragmentById(R.id.titles);

				if (isLeftClosed == false) {
					fragmentTransaction.hide(fragmentLeft);
					isLeftClosed = true;
				} else {
					fragmentTransaction.show(fragmentLeft);
					isLeftClosed = false;
				}

				fragmentTransaction.commit();

			}
		});
	}

}
