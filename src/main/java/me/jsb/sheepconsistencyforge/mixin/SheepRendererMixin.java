package me.jsb.sheepconsistencyforge.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.jsb.sheepconsistencyforge.client.SheepShearedLayerRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SheepRenderer;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
@Mixin(SheepRenderer.class)
public abstract class SheepRendererMixin extends MobRenderer<SheepEntity, SheepModel<SheepEntity>> {
	
	public SheepRendererMixin(EntityRendererManager renderManager, SheepModel<SheepEntity> model, float f) {
		super(renderManager, model, f);
	}

	@Inject(at = @At ("TAIL"), method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererManager;)V")
	private void sheep_consistency_forge_init(EntityRendererManager dispatcher, CallbackInfo info) {
		this.addLayer(new SheepShearedLayerRenderer(this));
	}
}
