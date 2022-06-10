package retail.controller.transaction.add;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.transaction.add.ReturnTransactionFacade;
import retail.shared.custom.CustomJDialog;
import retail.shared.eventlistener.action.transaction.returned.AddAction;
import retail.shared.eventlistener.action.transaction.returned.DeleteAction;
import retail.shared.eventlistener.document.transaction.SearchDocument;
import retail.shared.eventlistener.mouse.transaction.BotTableMouse;
import retail.shared.eventlistener.mouse.transaction.ListMouse;
import retail.shared.eventlistener.mouse.transaction.TopTableMouse;
import retail.shared.eventlistener.tablemodel.BotTableModel;
import retail.view.main.tab.bot.transaction.center.add.ReturnedTransactionCenter;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.ReturnedTransactionManipulator;
import retail.view.main.tab.top.UserPanel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReturnTransactionController {
    private final ReturnTransactionFacade facade;
    private final ReturnedTransactionManipulator manipulator;
    private final ReturnedTransactionCenter center;
    private final UserPanel userPanel;
    private final CustomJDialog returnDialog;

    public ReturnTransactionController(UserPanel userPanel,
                                       @NotNull ReturnedTransactionManipulator manipulator,
                                       @NotNull ReturnedTransactionCenter center,
                                       CustomJDialog returnDialog,
                                       ReturnTransactionFacade facade) {
        this.facade = facade;
        this.center = center;
        this.manipulator = manipulator;
        this.userPanel = userPanel;
        this.returnDialog = returnDialog;

        autoUpdateList();

        // ADD ALL - DELETE ALL - RETURN DIALOG - SAVE
        center.getTopTable().addMouseListener(new TopTableMouse(center,facade,returnDialog));
        center.getBotTable().addMouseListener(new BotTableMouse(manipulator,center,facade));
        center.getBotTable().getModel().addTableModelListener(new BotTableModel(center,facade));
        manipulator.getList().addMouseListener(new ListMouse(manipulator,center,facade));
        manipulator.getAdd().addActionListener(new AddAction(center,facade,returnDialog));
        manipulator.getDelete().addActionListener(new DeleteAction(manipulator,center,facade));
        manipulator.getSearch().getDocument().addDocumentListener(new SearchDocument(manipulator,facade));
    }

    private void autoUpdateList() {
        Runnable runnable = () -> {
            if(!manipulator.getSearch().getText().equals("")) return;
            if(manipulator.getList().isNotSameTransactionList(facade.getAllValidReport())) {
                manipulator.getList().populateTransactionList(facade.getAllValidReport());
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }
}
