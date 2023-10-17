package ma;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ma.ensa.StarAdapter;
import ma.ensa.StarService;
import ma.ensa.list.R;
import ma.ensa.Star;


public class ListActivity extends AppCompatActivity {
    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;

    private StarService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            String txt = "Evaluez vos personnes préférées";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }

    public void init() {
        service.create(new Star("Salwa DAHI", "https://media.licdn.com/dms/image/D4E35AQHowUpW9x2cqQ/profile-framedphoto-shrink_800_800/0/1697048286262?e=1697846400&v=beta&t=onu8lYnww8i3Ss0hG40wy_qCMz-QrMvVt6mg9Ml8JCo",5));
        service.create(new Star("Safiya DAOUDI", "https://media.licdn.com/dms/image/D4E03AQENEjbcx13cMQ/profile-displayphoto-shrink_800_800/0/1675886579622?e=1702512000&v=beta&t=5ITI__5EaqMBLyZsGGxmgj60JQksTHdCQJQdVLFptaU",5));
        service.create(new Star("Roa EL DHIMNI", "https://media.licdn.com/dms/image/D4E03AQFRGhzwy2brWg/profile-displayphoto-shrink_800_800/0/1675349749975?e=2147483647&v=beta&t=1JBgiz-7nyFE_RqYWIBuFWFdWxPy1jLLxECT9_C3o8Q",5));
        service.create(new Star("Hajar MACHMOUM", "https://media.licdn.com/dms/image/D4E03AQExJd-d-0hzYg/profile-displayphoto-shrink_800_800/0/1686318034456?e=2147483647&v=beta&t=7TpfVPHdmLZqbPUW-OFCXZUE3ZxdkVev5q5pn3jLbAA",5));
        service.create(new Star("Zakaria ELHARCHI", "https://media.licdn.com/dms/image/D4E03AQF1Yaux5K2Rmg/profile-displayphoto-shrink_800_800/0/1684540330852?e=1702512000&v=beta&t=AHCO7IVNVjazrQJQBeTTe0lh2IzgCFIPFx5nTJP7B6A", 4));
        service.create(new Star("Ilyass BENNANE", "https://media.licdn.com/dms/image/D4E03AQHRCjcjqgAiig/profile-displayphoto-shrink_800_800/0/1687186706269?e=2147483647&v=beta&t=XQI-TqGbH8DX-2076FBeGV2VhEi_FPaubU-4jVSEYfE", 3));
        service.create(new Star("Youssef Erasekh", "https://media.licdn.com/dms/image/D4E35AQGKTMKebqdTkg/profile-framedphoto-shrink_800_800/0/1684715709088?e=1697842800&v=beta&t=4ZkrXEBkgmrmQ_sTXj2hgUrMo-e9Ryv7FQVYITnHxZ4", 3));
    }
}