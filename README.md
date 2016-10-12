# Read From Json 
### Android Tutorial
This is tutorial on how to read from a JSON file for my Software Engineering class - Drake University.

##Content of this Tutorial
1. Source Code Usage
2. Tutorial

## Source Code Usage
1. Clone this repository

  ```
  $ git clone https://github.com/maheshgaya/ReadFromJson.git
  ```
2. Open Android Studio and import this project
  
  ```
  File -> Import Project -> (Choose the root of this project)
  ```

3. Run this app on an emulator with API greater than 16 (Jelly Bean or latest)


## Tutorial
1. Add the `.json` file into your `Assets Folder`

  Create the folder
  
  ```
  File -> New -> Folder -> Assets Folder
  ```
  
  Add a `.json` file to the folder. *You can read more about JSON [here](http://www.json.org/)*
  
  ```
  a. Right click on the assets folder in your project tree
  b. New -> File
  c. Give the file a name with the extension '.json'
  d. You can add your own JSON data in that file
  ```
  
  
2. (Optional) Create a 'Constant.java' file for storing the JSON tags that you want to retrieve.

  Class File Name: Constant.java

  ```
  public class Constant {
    //Tags for JSON
    public static final String JSON_COUNTY = "full_county_name"; //the tag for the full county name
    public static final String JSON_CITY_NAME = "name"; //the tag for the name of the city
    public static final String JSON_LONGITUDE = "primary_longitude"; //the tag for the longitude of the city
    public static final String JSON_LATITUDE = "primary_latitude"; //the tag for the latitude of the city
    public static final String JSON_IOWA = "iowa"; //the tag for the whole array for Iowa (Iowa rocks!)
  }
  ```
  
3. Create a model for the data you want to retrieve from the JSON

  Class File Name: City.java
  
  ```
  public class City {
    private String county; //full_county_name
    private String cityName; //name
    private String longitude; //primary_longitude
    private String latitude; //primary_latitude

    public City(String cityName, String county, String longitude, String latitude){
        this.cityName = cityName;
        this.county = county;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
  }
  ```

4. Create a custom adapter for your model

  Class File Name: CityAdapter.java
  
  ```
  public class CityAdapter extends BaseAdapter {
    private final String TAG = CityAdapter.class.getSimpleName();
    private Activity mContext;
    private List<City> mCityList;
    private static LayoutInflater mInflater = null;

    public CityAdapter(Activity context, City[] cities){
        mContext = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCityList = Arrays.asList(cities);
    }
    @Override
    public int getCount() {
        return mCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCityList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.listview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final City city = mCityList.get(position);

        viewHolder.txtCityName.setText(city.getCityName());
        viewHolder.txtCountyName.setText(city.getCounty());
        String longitude = mContext.getResources().getString(R.string.longitude) +
                city.getLongitude();
        viewHolder.txtLongitude.setText(longitude);
        String latitude = mContext.getResources().getString(R.string.latitude) +
                city.getLatitude();
        viewHolder.txtLatitude.setText(latitude);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, city.getCityName() + " tapped!", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    public static class ViewHolder{
        TextView txtCityName;
        TextView txtCountyName;
        TextView txtLongitude;
        TextView txtLatitude;
        private ViewHolder(View view){
            txtCityName = (TextView) view.findViewById(R.id.list_item_city_name);
            txtCountyName = (TextView) view.findViewById(R.id.list_item_county_name);
            txtLongitude = (TextView) view.findViewById(R.id.list_item_longitude);
            txtLatitude = (TextView) view.findViewById(R.id.list_item_latitude);
        }

    }
  }
  ```

5. Create your layout files with a listview to display all the data from the JSON file

  activity_main.xml
  
  ```
  <?xml version="1.0" encoding="utf-8"?>
  <FrameLayout
      xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:id="@+id/container_main"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".MainActivity"
      tools:ignore="MergeRootFrame">

  </FrameLayout>
  ```
  
  fragment_main.xml
  
  ```
  <?xml version="1.0" encoding="utf-8"?>

  <LinearLayout
      xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:orientation="vertical"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      tools:context=".MainActivity$MainFragment">
      <ListView
          tools:context="com.maheshgaya.android.readfromjson.MainActivity"
          android:id="@+id/listview_main"
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          />

  </LinearLayout>
  ```
  
  listview_item.xml
  
  ```
  <?xml version="1.0" encoding="utf-8"?>

  <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:id="@+id/list_item"
      android:padding="@dimen/activity_horizontal_margin"
      android:orientation="vertical">
      <TextView
          android:textColor="@color/colorBlack"
          tools:text="City"
          android:id="@+id/list_item_city_name"
          android:textSize="@dimen/text_title"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"/>
      <TextView
          tools:text="County"
          android:id="@+id/list_item_county_name"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"/>

      <TextView
          tools:text="Long: 50.0"
          android:id="@+id/list_item_longitude"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"/>
      <TextView
          tools:text="Lat: 50.0"
          android:id="@+id/list_item_latitude"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"/>


  </LinearLayout>
  ```
  
6. Now, create the activity

  Class File Name: MainActivity.java
  Description:
  
  ```
  public class MainActivity extends AppCompatActivity {

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          //this inflates the activity_main.xml
          setContentView(R.layout.activity_main);
          //Then it dynamically adds a fragment to the container_main in the activity_main.xml
          if (savedInstanceState == null) {
              getSupportFragmentManager().beginTransaction()
                      .add(R.id.container_main, new MainFragment())
                      .commit();
          }

      }
  }
  ```
  
7. Next, create the fragment
  Class File Name: MainFragment.java
  
  ```
  public class MainFragment extends Fragment {
      private City[] mCities = {}; //intialize
      private CityAdapter mCityAdapter; //use adapter to populate ListView
      private ListView mListView;

      public MainFragment(){
          //empty constructor required
      }
      @Nullable
      @Override
      public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          setRetainInstance(true); //this preserves the state of the fragment
          View rootView = inflater.inflate(R.layout.fragment_main, container, false);
          mCityAdapter = new CityAdapter(getActivity(), mCities);
          mListView = (ListView) rootView.findViewById(R.id.listview_main);
          mListView.setAdapter(mCityAdapter);

          return rootView;
      }

      @Override
      public void onStart() {
          super.onStart();
          //get cities
          updateCities();
      }

      public void updateCities(){
          FetchCityTask fetchCityTask = new FetchCityTask();
          fetchCityTask.execute();
      }

      /**
       * Get the cities in a background thread
       * This improves UX
       */
      //AsyncTask<params, progress, result>
      public class FetchCityTask extends AsyncTask<Void, Void, City[]>{

          private final String LOG_TAG = FetchCityTask.class.getSimpleName();
          @Override
          protected City[] doInBackground(Void... voids) {
              //get the cities from CityJsonReader
              List<City> cityList = new CityJsonReader(getActivity()).getCityList();
              //copy cityList into an Array
              City[] cities = cityList.toArray(new City[cityList.size()]);

              return cities;
          }

          @Override
          protected void onPostExecute(City[] results) {
              //if the results from the JSON is not empty, populate the ListView
              if (results != null){
                  mCities = results;
                  try {
                      mCityAdapter = new CityAdapter(getActivity(), mCities);
                      mCityAdapter.notifyDataSetChanged(); //callback to update the data in the adapter
                      mListView.setAdapter(mCityAdapter); //attach the adapter to the listView
                  } catch (UnsupportedOperationException e) {
                      Log.e(LOG_TAG, "onPostExecute: ", e);
                      e.printStackTrace();
                  }

              }
          }
      }
  }
  ```
  

You are all set. You can run your app to see your data.
