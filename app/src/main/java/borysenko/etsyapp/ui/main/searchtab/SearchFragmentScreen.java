package borysenko.etsyapp.ui.main.searchtab;


import borysenko.etsyapp.ui.main.searchtab.adapter.SearchRecyclerAdapter;
import borysenko.etsyapp.model.Category;
import borysenko.etsyapp.model.Merchandise;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 14:48
 */
public interface SearchFragmentScreen {
    interface View {
        void setSearchCategoryResult(Category[] search);

        void resultWithNoPic(Merchandise[] merchandises);
    }

    interface Presenter {
        void loadCategories();

        void getImageForTheMerchandise(Merchandise merchandise, SearchRecyclerAdapter adapter);
    }
}
