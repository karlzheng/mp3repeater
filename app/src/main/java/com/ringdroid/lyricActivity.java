/*
 * Copyright (C) 2008 Google Inc.
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
 */

package com.ringdroid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * The activity for the Ringdroid main editor window. Keeps track of the
 * waveform display, current horizontal offset, marker handles, start / end text
 * boxes, and handles all of the buttons and controls.
 */
public class lyricActivity extends Activity {
	private TextView txtLyric;
	private String strLyric;
	private String fileName;
	private String fullFileName;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.lyric_activity);
		txtLyric = (TextView) this.findViewById(R.id.lyricText);

		Intent intent = getIntent();
		fileName = intent.getStringExtra("mFilename");
		Log.i("lyricActivity", fileName);
		if ((fileName != null) && (fileName.length() > 0)) {
			int i = fileName.lastIndexOf('.');
			if ((i > -1) && (i < (fileName.length()))) {
				fileName = fileName.substring(0, i);
			}
		}
		
		fullFileName = fileName + ".lrc";
		File file=new File(fullFileName);
		
		if (!file.exists()) {
			fullFileName = fileName + ".txt";
			file=new File(fullFileName);			
		}
		
		if (file.exists()) {
			 FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         byte[] buf = new byte[1024];
	         StringBuffer strBuf = new StringBuffer();
	         try {
				while ((fis.read(buf)) != -1){
					strBuf.append(new String(buf));    
				     buf = new byte[1024];
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         strLyric = strBuf.toString();
		}
		
		txtLyric.setText(strLyric);		
	}

}
