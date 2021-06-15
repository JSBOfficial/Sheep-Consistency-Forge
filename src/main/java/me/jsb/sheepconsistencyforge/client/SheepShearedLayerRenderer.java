package me.jsb.sheepconsistencyforge.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.jsb.sheepconsistencyforge.SheepConsistencyForge;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;

public class SheepShearedLayerRenderer extends LayerRenderer<SheepEntity, SheepModel<SheepEntity>> {

	private final SheepModel<SheepEntity> model = new SheepModel<>();
    private static final ResourceLocation SKIN = new ResourceLocation(SheepConsistencyForge.MOD_ID, "textures/entity/sheep/sheep_sheared.png");
    
	public SheepShearedLayerRenderer(IEntityRenderer<SheepEntity, SheepModel<SheepEntity>> context) {
		super(context);
	}
	
	@Override
	   public void render(MatrixStack matrixStack, IRenderTypeBuffer vertexConsumerProvider, int i, SheepEntity sheepEntity, float f, float g, float h, float j, float k, float l) {
        float v;
        float w;
        float x;
        if (sheepEntity.hasCustomName() && "jeb_".equals(sheepEntity.getName().getContents())) {
            int n = sheepEntity.tickCount / 25 + sheepEntity.getId();
            int o = DyeColor.values().length;
            int p = n % o;
            int q = (n + 1) % o;
            float f3 = ((float)(sheepEntity.tickCount % 25) + h) / 25.0F;
            float[] afloat1 = SheepEntity.getColorArray(DyeColor.byId(p));
            float[] afloat2 = SheepEntity.getColorArray(DyeColor.byId(q));
            v = afloat1[0] * (1.0F - f3) + afloat2[0] * f3;
            w = afloat1[1] * (1.0F - f3) + afloat2[1] * f3;
            x = afloat1[2] * (1.0F - f3) + afloat2[2] * f3;
         } else {
            float[] afloat = SheepEntity.getColorArray(sheepEntity.getColor());
            v = afloat[0];
            w = afloat[1];
            x = afloat[2];
         }
        coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, SKIN, matrixStack, vertexConsumerProvider, i, sheepEntity, f, g, j, k, l, h, v, w, x);
    }
}
