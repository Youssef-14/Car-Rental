import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { StorageService } from '../../auth/components/services/storage/storage.service';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  if (StorageService.isLoggedIn()) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
