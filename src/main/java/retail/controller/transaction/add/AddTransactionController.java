package retail.controller.transaction.add;

import org.jetbrains.annotations.NotNull;
import retail.model.facade.transaction.add.AddTransactionFacade;
import retail.shared.eventlistener.action.transaction.add.*;
import retail.shared.eventlistener.document.transaction.DiscountAmountDocument;
import retail.shared.eventlistener.document.transaction.DiscountPercentageDocument;
import retail.shared.eventlistener.document.transaction.SoldDocument;
import retail.view.main.tab.bot.transaction.center.add.AddTransactionCenter;
import retail.view.main.tab.bot.transaction.manipulator.panel.add.AddTransactionManipulator;
import retail.view.main.tab.top.UserPanel;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AddTransactionController implements ItemListener {
    private final AddTransactionManipulator addManipulator;
    private final AddTransactionCenter addCenter;
    private final AddTransactionFacade addFacade;

    public AddTransactionController(UserPanel userPanel,
                                    @NotNull AddTransactionManipulator addManipulator,
                                    AddTransactionCenter addCenter,
                                    AddTransactionFacade addFacade) {
        this.addManipulator = addManipulator;
        this.addCenter = addCenter;
        this.addFacade = addFacade;
        autoUpdateProductList();
        autoCheckPrice();
        autoCalculateTotalAmount();
        addManipulator.getProduct().addItemListener(this);

        addManipulator.getGenerateId().addActionListener(new GenerateAction(addManipulator,addFacade));
        addManipulator.getClear().addActionListener(new ClearAction(addManipulator,addFacade));
        addManipulator.getDelete().addActionListener(new DeleteAction(addManipulator,addCenter,addFacade));
        addManipulator.getAdd().addActionListener(new AddAction(addManipulator,addCenter,addFacade));
        addManipulator.getSave().addActionListener(new SaveAction(addManipulator,addCenter,addFacade,userPanel.getLastNameText()));

        addManipulator.getSold().getDocument().addDocumentListener(new SoldDocument(addFacade,addManipulator));

        addManipulator.getDiscountPercent().getDocument().addDocumentListener(new DiscountPercentageDocument(addFacade,
                                                                                      addManipulator.getSoldTotal(),
                                                                                      addManipulator.getDiscountAmount(),
                                                                                      addManipulator.getDiscountPercent()));

        addManipulator.getDiscountAmount().getDocument().addDocumentListener(new DiscountAmountDocument(addFacade,
                                                                                 addManipulator.getSoldTotal(),
                                                                                 addManipulator.getDiscountAmount(),
                                                                                 addManipulator.getDiscountPercent()));
        addManipulator.getReportId().setText(addFacade.generateId()); // GENERATE ID AT START UP
    }

    private void autoCalculateTotalAmount() {
        addCenter.getTable().getModel().addTableModelListener(e -> {
            String[][] dataList = addFacade.tableGrabber(addCenter.getTable());
            addCenter.setTotal(addFacade.calculateReportAmount(dataList));
        });
    }

    private void autoUpdateProductList() {
        Runnable runnable = () -> {
            if(addManipulator.getProduct().isNotSameData(addFacade.getAllProduct())) {
                SwingUtilities.invokeLater(() -> addManipulator.getProduct().setProductIdList(addFacade.getAllProduct()));
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }

    private void autoCheckPrice() {
        Runnable runnable = () -> {
            if(addManipulator.getProduct().getSelectedItem() == null) return;
            String id = addManipulator.getProduct().getSelectedItem().toString();
            String price = addFacade.findPriceById(id);
            if(!addManipulator.getPrice().getText().equals(price)) {
                SwingUtilities.invokeLater(() -> addManipulator.getPrice().setText(price));
            }
        };
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(runnable, 1, 500, TimeUnit.MILLISECONDS);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        addFacade.clear(addManipulator);
    }
}
