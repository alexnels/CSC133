package com.mycompany.eventprog;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;

public class AssignmentFive extends Form {
    public AssignmentFive() {
        setLayout(new BorderLayout());

        //Right PointerContainer with the BoxLayout positioned on the west
        PointerContainer leftContainer = new PointerContainer("WEST");
        leftContainer.add(new Label("Left Container"));
        leftContainer.getAllStyles().setPadding(Component.TOP, 50);
        leftContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
        add(BorderLayout.WEST,leftContainer);

        //Center PointerContainer with the BoxLayout positioned on the center
        PointerContainer centerContainer = new PointerContainer("CENTER");
        centerContainer.add(new Label("Center Container"));
        centerContainer.getAllStyles().setPadding(Component.TOP, 50);
        centerContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
        add(BorderLayout.CENTER,centerContainer);

        //Right PointerContainer with the BoxLayout positioned on the right
        PointerContainer rightContainer = new PointerContainer("EAST");
        rightContainer.add(new Label("Right Container"));
        rightContainer.getAllStyles().setPadding(Component.TOP, 50);
        rightContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
        add(BorderLayout.EAST,rightContainer);

        //Bottom Container with the BoxLayout positioned on the center
        Container bottomContainer = new Container();
        bottomContainer.add(new Label("Bottom Container"));
        bottomContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
        add(BorderLayout.SOUTH,bottomContainer);

        show();
    }
}
