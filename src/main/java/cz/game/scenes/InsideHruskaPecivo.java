package cz.game.scenes;

import cz.game.Registry;
import cz.game.TheGame;
import cz.game.dialog.Dialog;
import cz.game.dialog.DialogStep;
import cz.game.inventory.Items;
import cz.game.objects.Item;
import cz.game.objects.State;
import cz.game.utils.Sound;

import java.awt.*;

public class InsideHruskaPecivo extends Scene {
    private final static String RESOURCE_PREFIX = "/scenes/inside_hruska/";

    public InsideHruskaPecivo() {
        this.setBackgroundImageUrl(TheGame.class.getResource("/images" + RESOURCE_PREFIX + "hruska_pecivo.png"));

        Item exkrement = new Item(RESOURCE_PREFIX + "hovno_kurata.png", State.DISCOVER, 816, 113,
                "Omrknout exkrement", Items.INSIDE_HRUSKA_HOVNO_KURATA);
        exkrement.setOnClickWithoutItem(() -> {
            Registry.add(Registry.Vals.HOVNO_U_KURAT_CHECKED);
            TheGame.instance.showMessage("Někdo se vysral do kuřat, no tohle!!");
        });
        this.objects.add(exkrement);

        Item phone = new Item(RESOURCE_PREFIX + "phone_empty.png", State.HANDLE, 63, 430, "Použít hlásič",
                Items.INSIDE_HRUSKA_PHONE);
        phone.setDialogPoint(new Point(95, 479));

        DialogStep phoneDialogStep = new DialogStep();
        Dialog phoneDialog = new Dialog(phone, phoneDialogStep);

        phoneDialogStep.setAnswer(". . .");
        DialogStep uklidHovnaStep = new DialogStep();
        uklidHovnaStep.setAnswer(" . . . ");
        uklidHovnaStep.setCondition((x) ->
                Registry.contains(Registry.Vals.HOVNO_U_KURAT_CHECKED) &&
                !Registry.contains(Registry.Vals.UKLID_KE_KURATUM_ZAVOLAN));
        uklidHovnaStep.setOnClick((dialog) ->  {
            TheGame.instance.playSound(Sound.UKLID_KE_KURATUM);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Registry.add(Registry.Vals.UKLID_KE_KURATUM_ZAVOLAN);
            dialog.loadDialogOptions(phoneDialogStep);
        });
        phoneDialogStep.addResponse("Zavolat úklid ke kuřatům.", uklidHovnaStep);

        phone.setOnClickWithoutItem(phoneDialog::start);

        this.objects.add(phone);
    }
}
