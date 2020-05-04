package com.papalam.help;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;

public class PainFragment extends Fragment {
    public static final int INPUT_FILE_REQUEST_CODE = 1;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Permissioner permissioner = new Permissioner(getActivity());
        if (!permissioner.checkPermissionForCamera())
            permissioner.requestPermissionForCamera();
        else {
            applyImage();
        }
        super.onActivityCreated(savedInstanceState);
    }

    public void applyImage() {
        Intent[] intentArray;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentArray = new Intent[]{takePictureIntent};
        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");
        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Прикрепить изображение");
        startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        applyImage();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            // If it returns multiple item from storage
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                for (int i = 0; i < mClipData.getItemCount(); i++) {
                    ClipData.Item item = mClipData.getItemAt(i);
                    Uri uri = item.getUri();
                }
            }
            // Single item selected
            else if (data.getClipData() == null && data.getDataString() != null) {
                String dataString = data.getDataString();
                if (dataString != null) {
                }
            }
            //Camera used
            else {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), photo, "Title", null);
            }
        }
    }
}