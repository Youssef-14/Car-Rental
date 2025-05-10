import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { StorageService } from '../../auth/components/services/storage/storage.service';

export const adminGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  if (StorageService.isAdminLoggedIn()) {
    return true;
  } else if (StorageService.isLoggedIn()) {
    router.navigate(['/dashboard']);
    return false;
  }else {
    router.navigate(['/login']);
    return false;
  }
};

