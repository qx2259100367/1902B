package presenter;

import base.BasePresenter;
import model.CommentModel;
import view.CommentView;

/**
 * Created by ASUS on 2019/9/9.
 */

public class CommentPresenter extends BasePresenter<CommentView> {

    private CommentModel commentModel;

    @Override
    protected void initModel() {
        commentModel = new CommentModel();
        addModel(commentModel);
    }
}
