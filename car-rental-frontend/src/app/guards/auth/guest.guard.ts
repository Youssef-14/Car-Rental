import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { StorageService } from '../../auth/components/services/storage/storage.service';

export const guestGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  if (!StorageService.isLoggedIn()) {
    return true;
  }if(StorageService.isAdminLoggedIn()) {
    router.navigate(['/admin']);
    return false;
  } if(StorageService.isCustomerLoggedIn()) {
    router.navigate(['/customer']);
    return false;
  }
  return false;
};
