package com.example.jacek.cobytudemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static final String mcKey = "com.cobytu.app.dania";

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        implementSubcategories(tabLayout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //Search
       /* Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("cobytu", "query: " + query);
//            doMySearch(query);
        }
*/

    }

    private void implementSubcategories(TabLayout tabLayout) {
        LinearLayout tabStrip = (LinearLayout) tabLayout.getChildAt(0);


        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            final int iFinal = i;

            tabStrip.getChildAt(i).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    boolean create_dialog = true;
                    SubcategoriesAdapter adapter = null;
                    Holder holder = new ListHolder();
                    boolean isGrid = false;
                    switch (iFinal) {
                        case 0:
                            adapter = new SubcategoriesAdapter(MainActivity.this, isGrid, getResources().getStringArray(R.array.snaps_subcategories));

                            break;
                        case 1:
                            adapter = new SubcategoriesAdapter(MainActivity.this, isGrid, getResources().getStringArray(R.array.soups_subcategories));
                            break;
                        case 2:
                            adapter = new SubcategoriesAdapter(MainActivity.this, isGrid, getResources().getStringArray(R.array.salads_subcategories));
                            break;
                        case 3:
                            adapter = new SubcategoriesAdapter(MainActivity.this, isGrid, getResources().getStringArray(R.array.main_courses_subcategories));
                            break;
                        case 4:
                            adapter = new SubcategoriesAdapter(MainActivity.this, isGrid, getResources().getStringArray(R.array.kids_subcategories));
                            break;
                        case 5:
                            adapter = new SubcategoriesAdapter(MainActivity.this, isGrid, getResources().getStringArray(R.array.desserts_subcategories));
                            break;
                        case 6:
                            adapter = new SubcategoriesAdapter(MainActivity.this, isGrid, getResources().getStringArray(R.array.beverages_subcategories));
                            break;
                        case 7:
                            create_dialog = false;
                            break;
                    }


                    DialogPlus dialog = (create_dialog) ? DialogPlus.newDialog(MainActivity.this)
                            .setContentHolder(holder)
                            .setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                                    Log.d("cobytu", "DialogPlus " + "onItemClick() called with: " + "item = [" +
                                            item + "], position = [" + position + "]" + " iFinal: " + iFinal);
                                }
                            })
                            .setGravity(Gravity.CENTER)
                            .setAdapter(adapter)
                            .setExpanded(false)

                            .setCancelable(true)
                            .setContentBackgroundResource(R.color.colorPrimary)
                            .create() : null;

                    if (dialog != null) {
                        dialog.show();
                    }
//                    Toast.makeText(MainActivity.this, "Kliknalem " + chooseTabName(iFinal), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);



/*
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        SharedPreferences prefs = this.getSharedPreferences(
                "com.cobytu.app", Context.MODE_PRIVATE);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_download) {

            processMainCourses(prefs);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements RecyclerViewAdapterMainCourses.ClickListener, SearchView.OnQueryTextListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private RecyclerView recyclerView;
        private RelativeLayout rlInfo;
        private RecyclerViewAdapterMainCourses recyclerAdapter;
        private int mainCourseCategoryID = 3;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            if (getArguments() != null) {
                mainCourseCategoryID = getArguments().getInt(ARG_SECTION_NUMBER);
            }
            initialize(rootView);

            return rootView;
        }


        public void initialize(View v) {
            recyclerView = (RecyclerView) v.findViewById(R.id.rv_mainCourses);
            rlInfo = (RelativeLayout) v.findViewById(R.id.rl_info);

        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {

            read(getActivity());
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.menu_main, menu);

            final MenuItem item = menu.findItem(R.id.search);
            final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
            searchView.setOnQueryTextListener(this);
        }

        public void read(Context context) {
            JSONArray jsonArrayMainCourses = null;
            try {
                SharedPreferences prefs = context.getSharedPreferences(
                        "com.cobytu.app", Context.MODE_PRIVATE);
                jsonArrayMainCourses = new JSONArray(prefs.getString(mcKey, null));
                parseMainCourseListFromJSONArray(jsonArrayMainCourses);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("cobytu", getClass().getName() + " in read()" + e.getLocalizedMessage());
            }
        }

        public void parseMainCourseListFromJSONArray(JSONArray jsonArray) {
            List<MainCourse> userMainCourses = new ArrayList<>();
            List<MainCourse> userMainCoursesTemp = new ArrayList<>();
            MainCourse mainCourseObject = null;

            Log.d("cobytu", getClass().getName() + "ilosc dan: " + jsonArray.length() + " przystepuje do parsowania");

            try {
        /*	Log.d("cobytu", getClass().getName() + " Petla parsowania JSONArray do obiekt�w MainCourses:");
            Log.d("cobytu", getClass().getName() + " userMainCourse.size() przed parsowaniem: " + userMainCourses.size());*/

                for (int mcID = 0; mcID < jsonArray.length(); mcID++) {
                    JSONObject tempMainCourseObject = null;
                    JSONArray variantsList = null;
                    JSONArray variantsList1 = null;
                    JSONArray variantsList2 = null;

                    tempMainCourseObject = jsonArray.getJSONObject(mcID);

                    //poniższe linijki są po to, żeby uzupełnic do_wariant wartościami domyslnymi (tymi na idx 0) z list wariantowej 1 i/lub 2
                    variantsList = tempMainCourseObject.getJSONArray(KEY_ADDITIONAL_VARIANT);
                    variantsList1 = tempMainCourseObject.getJSONArray(KEY_ADDITIONALS_VARIANT_LIST1);
                    variantsList2 = tempMainCourseObject.getJSONArray(KEY_ADDITIONALS_VARIANT_LIST2);

                    if (variantsList.length() == 0) {
                        if (variantsList1.length() != 0) {
                            if (variantsList2.length() != 0) {
                                variantsList.put(0, variantsList1.getString(0));
                                variantsList.put(1, variantsList2.getString(0));
                            } else {
                                variantsList.put(0, variantsList1.getString(0));
                            }
                        }
                    }
                    mainCourseObject = new MainCourse(mcID,
                            tempMainCourseObject.getString(KEY_MAIN_COURSE),
                            tempMainCourseObject.getString(KEY_RESTAURATEUR_ID),
                            tempMainCourseObject.getString(KEY_PHOTO_URL),
                            tempMainCourseObject.getString(KEY_RESTAURANT_NAME),
                            tempMainCourseObject.getString(KEY_MAIN_COURSE_CATEGORY_ID),
                            tempMainCourseObject.getString(KEY_MAIN_COURSE_TYPE),
                            tempMainCourseObject.getString(KEY_MAIN_COURSE_NAME),
                            tempMainCourseObject.getString(KEY_MAIN_COURSE_DESCRIPTION),
                            tempMainCourseObject.getString(KEY_MAIN_COURSE_AVERAGE_EVALUATION),
                            tempMainCourseObject.getJSONArray(KEY_ADDITIONAL_PRIMARY).toString(),
                            variantsList.toString(),
                            tempMainCourseObject.getJSONArray(KEY_ADDITIONALS_VARIANT_LIST1).toString(),
                            tempMainCourseObject.getJSONArray(KEY_ADDITIONALS_VARIANT_LIST2).toString(),
                            tempMainCourseObject.getJSONArray(KEY_ADDITIONALS_ADDITIONAL_LIST).toString(),
                            tempMainCourseObject.getJSONArray(KEY_ALLERGENS).toString(),
                            tempMainCourseObject.getString(KEY_PRICE_BASIC),
                            tempMainCourseObject.getString(KEY_PRICE),
                            tempMainCourseObject.getString(KEY_PREPARATION_TIME),
                            tempMainCourseObject.getInt(KEY_WEIGHT),
                            tempMainCourseObject.getInt(KEY_KCAL),
                            tempMainCourseObject.getString(KEY_EVALUATION),
                            "Jacek",
                            2,3
                    );
//				Log.d("cobytu", getClass().getName() + " userMainCourses.add("+mcID+","+mainCourseObject.getMainCourseName()+"(id:"+mainCourseObject.getMainCourseID()+"))");
                    userMainCoursesTemp.add(mcID, mainCourseObject);

                }
//			Log.d("cobytu", getClass().getName() + " userMainCourses.size() po parsowaniu: "+ userMainCourses.size());
//Zmiana 06-01-2016
//			if(mainCourseCategoryID>0){
                Log.d("cobytu", "Ilsoc dan 1 : " + String.valueOf(userMainCourses.size()));
                userMainCourses = getAppropriateDataDependingOnCategory(userMainCoursesTemp);
//			}
                Log.d("cobytu", "Ilsoc dan 3 : " + String.valueOf(userMainCourses.size()));
                loadList(userMainCourses);

            } catch (JSONException e) {
                Log.e("cobytu", getClass().getName() + " JSONError: " + e.toString());
                e.printStackTrace();
            }

        }

        private List<MainCourse> getAppropriateDataDependingOnCategory(List<MainCourse> userMainCourses) {
            List<MainCourse> userMainCoursesCurrentList = new ArrayList<>();
            Log.d("cobytu",
                    "[FragmentMainCourses] loadList()  (userMainCourses != null && mainCourseCategoryID > 0) ");
            userMainCoursesCurrentList.clear();
            int counter = 0;
            Log.d("cobytu", "Ilsoc dan 2 : " + String.valueOf(userMainCourses.size()));
//        for (int i = 0; i < ((userMainCourses != null && !userMainCourses.isEmpty()) ? userMainCourses.size() : db.getUserMainCoursesBatchInfo(DatabaseHandler.getKEY_USER_MAIN_COURSES_TOTAL())); i++) {
            for (int i = 0; i < userMainCourses.size(); i++) {
                if (Integer.valueOf(userMainCourses.get(i).getMainCourseCategoryID()) == mainCourseCategoryID) {
                    userMainCoursesCurrentList.add(counter, userMainCourses.get(i));
                    counter++;
                }

            }

            return userMainCoursesCurrentList;
        }


        private void loadList(List<MainCourse> list) {
            mainlist = list;
            if (getActivity() != null) {
        /*	Log.d("cobytu",getClass().getName() + " userMainCourseToDisplayInner ("+userMainCourseToDisplayInner.size()+
					"), userMainCoursesInner("+userMainCoursesInner.size()+")");
*/
//              recyclerAdapter = new RecyclerViewAdapterMainCourses(getActivity(), (userMainCourses.size()+1 >= numberOfMCperPage) ? userMainCourseToDisplayInner : userMainCoursesInner);
                recyclerAdapter = new RecyclerViewAdapterMainCourses(getActivity(), list);
                recyclerAdapter.setClickListener(PlaceholderFragment.this);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            } else {
                Log.d("cobytu", "Za szybko, od�wie� liste da�");
            }
        }

        private List<MainCourse> mainlist;


        @Override
        public void itemClick(View view, int position) {
            Intent i = new Intent(getActivity(), MainCourseDetailsActivity.class);
            i.putExtra(MainCourseDetailsActivity.MAIN_COURSE_OBJECT, (Serializable) mainlist.get(position));
            startActivityForResult(i, 1);
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String query) {
            final List<MainCourse> filteredModelList = filter(mainlist, query);
            recyclerAdapter.animateTo(filteredModelList);
            recyclerView.scrollToPosition(0);

            return true;
        }

        private List<MainCourse> filter(List<MainCourse> mainCourses, String query) {
            query = query.toLowerCase();

            final List<MainCourse> filteredModelList = new ArrayList<>();
            for (MainCourse mainCourse : mainCourses) {
                final String text = mainCourse.getMainCourseName().toLowerCase();
                if (text.contains(query)) {
                    filteredModelList.add(mainCourse);
                }
            }
            return filteredModelList;
        }


    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {

            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return chooseTabName(position);
        }


    }

    private String chooseTabName(int idx) {
        String tabTitle = "Kategoria";
        switch (idx) {
            case 0:
                tabTitle = MainActivity.this.getString(R.string.tabTitleSnacks);
                break;
            case 1:
                tabTitle = MainActivity.this.getString(R.string.tabTitleSoups);
                break;
            case 2:
                tabTitle = MainActivity.this.getString(R.string.tabTitleSalads);
                break;
            case 3:
                tabTitle = MainActivity.this.getString(R.string.tabTitleMainCourses);
                break;
            case 4:
                tabTitle = MainActivity.this.getString(R.string.tabTitleForKids);
                break;
            case 5:
                tabTitle = MainActivity.this.getString(R.string.tabTitleDesserts);
                break;
            case 6:
                tabTitle = MainActivity.this.getString(R.string.tabTitleBeverages);
                break;
            case 7:
                tabTitle = MainActivity.this.getString(R.string.tabTitleAlcohol);

                break;
        }

        return tabTitle;
    }


    public void processMainCourses(final SharedPreferences prefs) {
        //ProcessMainCourses
        StringRequest processMainCoursesGETrequest = new StringRequest(Request.Method.GET, "http://www.cobytu.com/cbt.php?d=j_przegladaj_dania5&next=1000", //Zmiana 06-01-2015
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("cobytu", "[MainActivity] ProcessMainCourses.result: " + response);

                        String regex = "{";
//                        UsefulFunctions.largeLog("cobytu", getClass().getName() + " refresh response (JSON zaczyna sie na " + response.indexOf(regex) + "-tym indexie): " + response.toString());

                        if (response.indexOf(regex) > 0) {
                            /*String repairedResponse = response.substring(response.indexOf(regex));*/
                            response = response.substring(response.indexOf(regex));
//                            UsefulFunctions.largeLog("cobytu", getClass().getName() + " zatem poprawiam response. Teraz JSON zaczyna sie na " + response.indexOf(regex) + "-tym indexie):Nowe response: " + response);
                        }

                        try {
                            JSONObject responseJSONObject = new JSONObject(response);

                            prefs.edit().putString(mcKey, responseJSONObject.getJSONArray("dania").toString()).apply();

                            Log.d("cobytu", prefs.getString(mcKey, "nie ma danych"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("cobytu", getClass().getName() + " processMainCoursesGETrequest Volley error: " + error.getMessage());
                VolleyLog.d(getClass().getName() + " processMainCoursesGETrequest Error: ", error.getMessage());

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.error_network_timeout), Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.error_auth_failure), Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.error_server), Toast.LENGTH_LONG).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.error_network), Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.error_parser), Toast.LENGTH_LONG).show();
                }


            }
        });


        processMainCoursesGETrequest.setShouldCache(false);
        App.getInstance().getRequestQueue().add(processMainCoursesGETrequest);
    }


    static final String KEY_MAIN_COURSE = "da_id";
    static final String KEY_RESTAURATEUR_ID = "da_uz_id";
    static final String KEY_PHOTO_URL = "da_foto";
    static final String KEY_RESTAURANT_NAME = "da_gdzie";
    static final String KEY_MAIN_COURSE_CATEGORY_ID = "da_kategoria";
    static final String KEY_MAIN_COURSE_TYPE = "da_rodzaj";
    static final String KEY_MAIN_COURSE_NAME = "da_nazwa";
    static final String KEY_MAIN_COURSE_DESCRIPTION = "da_opis";
    static final String KEY_MAIN_COURSE_AVERAGE_EVALUATION = "da_srednia";
    static final String KEY_ADDITIONAL_PRIMARY = "do_podst";
    static final String KEY_ADDITIONAL_VARIANT = "do_wariant";
    static final String KEY_ADDITIONALS_VARIANT_LIST1 = "do_wariant_lista1";
    static final String KEY_ADDITIONALS_VARIANT_LIST2 = "do_wariant_lista2";
    static final String KEY_ADDITIONALS_ADDITIONAL_LIST = "do_dodat";
    static final String KEY_ALLERGENS = "alergeny";
    static final String KEY_PRICE_BASIC = "cena_podst";
    static final String KEY_PRICE = "cena";
    static final String KEY_PREPARATION_TIME = "da_czas";
    static final String KEY_WEIGHT = "waga";
    static final String KEY_KCAL = "kcal";
    static final String KEY_EVALUATION = "ud0_da_lubi";
}
