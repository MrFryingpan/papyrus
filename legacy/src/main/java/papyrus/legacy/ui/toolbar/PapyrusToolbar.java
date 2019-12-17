package papyrus.legacy.ui.toolbar;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

import papyrus.toolbar.tabs.TabbedPapyrusToolbar;

/**
 * @deprecated - Use PapyrusToolbar, or TabbedPapyrusToolbar as desired.
 */
public class PapyrusToolbar extends TabbedPapyrusToolbar {

    public PapyrusToolbar(@NonNull Context context) {
        super(context);
    }

    public PapyrusToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}