package com.andela.suada.suchef.views;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.andela.suada.suchef.R;
import com.andela.suada.suchef.adapters.CategoriesAdapter;
import com.andela.suada.suchef.model.Category;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CategoryListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CategoriesAdapter adapter;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        categoryList = new ArrayList<>();
        adapter = new CategoriesAdapter(this, categoryList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareCategory();

        try{
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        // hiding & showing the title when toolbar expanded & collapsed
        if (appBarLayout != null) {
            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean isShown = false;
                int scrollRange = -1;
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if(scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    } if (scrollRange + verticalOffset == 0) {
                        collapsingToolbar.setTitle(getString(R.string.app_name));
                        isShown = true;
                    } else if(isShown) {
                        collapsingToolbar.setTitle(" ");
                        isShown = false;
                    }

                }
            });
        }
    }
    private void prepareCategory() {
        int[] covers = new int[] {
                R.drawable.category1,
                R.drawable.category2,
                R.drawable.category3,
                R.drawable.category4,
                R.drawable.category5
        };
        Category category = new Category("Desserts", 13, covers[0]);
        categoryList.add(category);

        category = new Category("Fruits", 13, covers[1]);
        categoryList.add(category);

        category = new Category("Vegetables", 13, covers[2]);
        categoryList.add(category);

        category = new Category("Sauce", 13, covers[3]);
        categoryList.add(category);

        category = new Category("Cakes", 13, covers[4]);
        categoryList.add(category);

        adapter.notifyDataSetChanged();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
