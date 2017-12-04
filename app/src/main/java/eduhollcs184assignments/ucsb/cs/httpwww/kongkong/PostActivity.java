package eduhollcs184assignments.ucsb.cs.httpwww.kongkong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;

class PostActivity extends AppCompatActivity {


    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference postRef = rootRef.child("Posts");
    FirebaseAuth myAuth = FirebaseAuth.getInstance();
    String email = myAuth.getCurrentUser().getEmail();

    EditText Title, Location, Email, Description;
    ImageView button;

    //spinner for different topics
    //Spinner spinner;
    //ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.choices, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Title = (EditText) findViewById(R.id.titleeditText);
        Location = (EditText) findViewById(R.id.locationeditText3);
        //Email = (EditText) findViewById(R.id.emaileditText4);
        Description = (EditText) findViewById(R.id.deseditText5);
        button = (ImageView) findViewById(R.id.postbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Animation myAnim = AnimationUtils.loadAnimation(PostActivity.this, R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                button.startAnimation(myAnim);
                String t = Title.getText().toString();
                String l = Location.getText().toString();
                //String e = Email.getText().toString();
                String d = Description.getText().toString();
                String s = spinner.getSelectedItem().toString();


                HashMap<String,String> posts_map = new HashMap<>();
                posts_map.put("Title", t);
                posts_map.put("Location", l);
                posts_map.put("Email", email);
                posts_map.put("Description", d);
                posts_map.put("Topic", s);

                postRef.push().setValue(posts_map);


            }
        });

    }
}