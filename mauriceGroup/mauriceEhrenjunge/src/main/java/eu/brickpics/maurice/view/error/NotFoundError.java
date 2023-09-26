package eu.brickpics.maurice.view.error;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
import eu.brickpics.maurice.config.security.SecurityUtils;
import eu.brickpics.maurice.view.main.MainView;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;

@Tag(Tag.DIV)
@ParentLayout(value = MainView.class)
@PageTitle("404 - Site not found")
@CssImport("./styles/views/error/error-view.css")
public class NotFoundError extends HorizontalLayout implements HasErrorParameter<NotFoundException> {
    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
        if (!SecurityUtils.isUserLoggedIn()) {
            UI.getCurrent().getPage().setLocation("/login");
        }

        Location location = event.getLocation();
        this.setHeightFull();
        this.setAlignItems(Alignment.CENTER);
        add(new H1("              Shut up Idiot"));
        add(new H5("            " + location.getPath()));
        return HttpServletResponse.SC_NOT_FOUND;
    }
}
